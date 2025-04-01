package pl.matzysz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.matzysz.domain.User;
import pl.matzysz.domain.VerificationToken;
import pl.matzysz.repository.VerificationTokenRepository;
import pl.matzysz.utils.TokenGenerator;

import java.time.LocalDateTime;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    public final VerificationTokenRepository verificationTokenRepository;


    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public VerificationToken generateVerificationToken(User user) {
        String token = TokenGenerator.generateToken(35);

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(1));

        return verificationTokenRepository.save(verificationToken);
    }

    @Transactional
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public VerificationToken getVerificationTokenByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    public User getUserByToken(String token) {
        return verificationTokenRepository.findByToken(token).getUser();
    }

    @Transactional
    public void deleteVerificationToken(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }

}
