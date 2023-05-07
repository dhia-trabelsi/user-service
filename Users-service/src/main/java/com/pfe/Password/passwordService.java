package com.pfe.Password;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfe.auth.Util.EmailRequest;
import com.pfe.auth.Util.EmailSender;
import com.pfe.user.User;
import com.pfe.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class passwordService {

    private final UserRepository repository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailSender emailSender;

    public String changePassword(@RequestBody changePassword request, int userId) {

        User user = repository.findById(userId).orElseThrow();

        String encodedPassword = user.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(request.getOldPassword(), encodedPassword)) {
            return "error";
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);

        return "password changed";
    }

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        tokenRepository.save(passwordResetToken);
    }

    public String validatePasswordResetToken(String passwordResetToken) {
        PasswordResetToken passwordToken = tokenRepository.findByToken(passwordResetToken);
        if (passwordToken == null) {
            return "Invalid password rest token";
        }
        User user = passwordToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((passwordToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            return "Link already expired, resend link";
        }
        return "valid";
    }

    public Optional<User> findUserByPasswordToken(String passwordResetToken) {
        return Optional.ofNullable(tokenRepository.findByToken(passwordResetToken).getUser());
    }

    public PasswordResetToken findPasswordResetToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public String passwordResetEmailLink(User user, String applicationUrl, String passwordToken) {
        String url = applicationUrl + "/api/password/reset-password?token=" + passwordToken;
        EmailRequest emailRequest = new EmailRequest();

        emailRequest.setSubject("Password Reset Request");

        emailRequest.setText("<p> Hi, " + user.getFirstname() + ", </p>" +
                "<p><b>You recently requested to reset your password,</b>" + "" +
                "Please, follow the link below to complete the action.</p>" +
                "<a href=\"" + url + "\">Reset password</a>" +
                "<p> Users Registration Portal Service");

        emailRequest.setTo(user.getEmail());

        emailSender.sendEmail(emailRequest);
        return url;
    }

}
