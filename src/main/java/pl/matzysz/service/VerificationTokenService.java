package pl.matzysz.service;

import pl.matzysz.domain.User;
import pl.matzysz.domain.VerificationToken;

public interface VerificationTokenService {
    VerificationToken generateVerificationToken(User user);
    VerificationToken getVerificationToken(String token);
    VerificationToken getVerificationTokenByUser(User user);
    User getUserByToken(String token);
    void deleteVerificationToken(VerificationToken verificationToken);
}
