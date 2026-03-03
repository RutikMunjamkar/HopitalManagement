package com.example.demo.security;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.SignUpResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exception.CustomException;
import com.example.demo.repository.UserRepository;
import com.example.demo.type.AuthProviderType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;

    private final UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())) ;
        User user= (User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public User SignUpInternal(LoginRequestDto signUpRequestDto, AuthProviderType authProviderType, String providerId){
        User user= (User) userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);
        if(user!=null){
            throw new CustomException("user already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User cratedUser=User.builder().username(signUpRequestDto.getUsername()).password(passwordEncoder.encode(signUpRequestDto.getPassword())).authProviderType(authProviderType).providerId(providerId).build();
        if(authProviderType==AuthProviderType.EMAIL){
            cratedUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        }
        return userRepository.save(cratedUser);
    }

    //
    //@Transactional
    public SignUpResponseDto signUp(LoginRequestDto signUpRequestDto) throws CustomException{
        User user=SignUpInternal(signUpRequestDto,AuthProviderType.EMAIL,null);
        return objectMapper.convertValue(user,SignUpResponseDto.class);
    }

    @Transactional
    public ResponseEntity<LoginResponseDto>handleOAuth2LoginService(OAuth2User oAuth2User, String registrationId) throws CustomException{
        //fetch provider type and providerId
        //save the provider type and provider id info with user
        // if the user has an account ->direct login
        //if no account for user ->signup
        AuthProviderType authProviderType=authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId=authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);
        User user=userRepository.findByProviderIdAndAuthProviderType(providerId,authProviderType).orElse(null);
        String email=oAuth2User.getAttribute("email");
        User emailUser= (User) userRepository.findByUsername(email).orElse(null);

        if(user==null && emailUser ==null){
            //signUpFlow
            String username= authUtil.determineUserNameFromOAuthUser(oAuth2User,registrationId,providerId);
            user = SignUpInternal(new LoginRequestDto(username,null),authProviderType,providerId);
        }
        else if(user!=null){
            if(email!=null & !email.isBlank() && !email.equalsIgnoreCase(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }
        else{
            //emailUser is present but the user is not
            throw new BadCredentialsException("this email is already registered with : " +emailUser.getAuthProviderType());
        }
        LoginResponseDto loginResponseDto=new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    };


}
