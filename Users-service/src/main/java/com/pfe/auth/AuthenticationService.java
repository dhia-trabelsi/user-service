package com.pfe.auth;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pfe.Util.EmailRequest;
import com.pfe.Util.EmailSender;
import com.pfe.Util.HistoryRequest;
import com.pfe.Util.HistorySender;
import com.pfe.Util.NotifRequest;
import com.pfe.Util.NotifSender;
import com.pfe.config.JwtService;
import com.pfe.token.Token;
import com.pfe.token.TokenRepository;
import com.pfe.token.TokenType;
import com.pfe.user.Child;
import com.pfe.user.ChildRepository;
import com.pfe.user.ChldDto;
import com.pfe.user.Role;
import com.pfe.user.User;
import com.pfe.user.UserRepository;
import com.pfe.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final UserService userService;
  private final ChildRepository childRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final NotifSender notifSender;
  private final EmailSender emailSender;
  private final HistorySender historySender;

  LocalDateTime localDateTime = LocalDateTime.now();
  Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .phone(request.getPhone())
        .address(request.getAddress())
        .age(request.getAge())
        .sexe(request.getSexe())
        .cin(request.getCin())
        .coinjoint(request.getCoinjoint())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_USER)
        .societeId(request.getSocieteId())
        .build();

    System.out.println("Request children: " + request.getChildren());

    var savedUser = repository.save(user);
    if (request.getChildren() != null) {
      for (ChldDto childDto : request.getChildren()) {
        Child child = Child.builder()
            .name(childDto.getName())
            .birthDate(childDto.getBirthDate())
            .user(savedUser)
            .build();
            childRepository.save(child);
        user.getChildren().add(child);
      }
    }
    
    
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

    List<User> admins = repository.findAllByRole(Role.ROLE_SUPER_ADMIN);

    NotifRequest notifRequest = new NotifRequest();
    notifRequest.setDate(date);
    notifRequest.setType("NEW_ADHERENT");


    for (User admin : admins) {

      notifRequest.setMessage("nouveau adherent : " + user.getFirstname() + " " + user.getLastname() + " a rejoint la societe "
      + user.getSocieteId());
      notifRequest.setUser(admin.getId());
      notifSender.sendNotif(notifRequest);
    }

    EmailRequest emailRequest = new EmailRequest();
    emailRequest.setTo(user.getEmail());
    emailRequest.setSubject("Bienvenue chez nous");
    String body = "Bonjour " + user.getFirstname() + " " + user.getLastname()
        + ",\n\nNous vous souhaitons la bienvenue chez nous. votre Email : " + user.getEmail()
        + " votre mot de passe : " + request.getPassword() + "\n\nCordialement,\n\nL'équipe de gestion de la société "
        + user.getSocieteId();
    emailRequest.setText(body);

    emailSender.sendEmail(emailRequest);

    HistoryRequest historyRequest = new HistoryRequest();
    historyRequest.setDate(date);
    historyRequest.setType("ADHERENT");
    historyRequest.setUser(userService.getAuthenticatedUser().getId());
    historyRequest.setMessage("nouveau adherent : " + user.getFirstname() + " " + user.getLastname() + " a rejoint la societe "
    + user.getSocieteId());
    historySender.sendHistory(historyRequest);


    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse registerAdmins(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .phone(request.getPhone())
        .address(request.getAddress())
        .age(request.getAge())
        .sexe(request.getSexe())
        .cin(request.getCin())
        .coinjoint(request.getCoinjoint())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_ADMIN)
        .build();

   
    
    var savedUser = repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

///////////////////////////

   

    List<User> admins = repository.findAllByRole(Role.ROLE_SUPER_ADMIN);
    
    NotifRequest notifRequest = new NotifRequest();
    notifRequest.setDate(date);
    notifRequest.setType("NEW_ADMIN");


    for (User admin : admins) {
      notifRequest.setMessage("nouveau Admin : " + user.getFirstname() + " " + user.getLastname() + " a rejoint la societe "
      + user.getSocieteId());
      notifRequest.setUser(admin.getId());
      notifSender.sendNotif(notifRequest);
    }

    HistoryRequest historyRequest = new HistoryRequest();
    historyRequest.setDate(date);
    historyRequest.setType("ADMIN");
    historyRequest.setMessage("nouveau admin : " + user.getFirstname() + " " + user.getLastname() + " a rejoint la societe "
    + user.getSocieteId());
    historyRequest.setUser(userService.getAuthenticatedUser().getId());
    historySender.sendHistory(historyRequest);


    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()));
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);


    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findValidAllTokenByUser(user);
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
