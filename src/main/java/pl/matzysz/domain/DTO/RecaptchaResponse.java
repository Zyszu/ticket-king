package pl.matzysz.domain.DTO;

import java.sql.Timestamp;
import java.util.List;

public class RecaptchaResponse {
    private boolean success;
    private Timestamp challenge_ts;
    private String hostname;
    private List<String> error_codes;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public Timestamp getChallenge_ts() { return challenge_ts; }
    public void setChallenge_ts(Timestamp challenge_ts) { this.challenge_ts = challenge_ts; }

    public String getHostname() { return hostname; }
    public void setHostname(String hostname) { this.hostname = hostname; }

    public List<String> getError_codes() { return error_codes; }
    public void setError_codes(List<String> error_codes) {  this.error_codes = error_codes; }

    @Override
    public String toString() {
        return "RecaptchaResponse{" +
                "success=" + success +
                ", challenge_ts=" + challenge_ts +
                ", hostname='" + hostname + '\'' +
                ", error_codes=" + error_codes +
                '}';
    }

}
