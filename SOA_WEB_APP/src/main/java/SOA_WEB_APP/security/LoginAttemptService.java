package SOA_WEB_APP.security;

import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 20;
    Set<RequestLimitRule> rules =
            Collections.singleton(RequestLimitRule.of(60, TimeUnit.MINUTES, MAX_ATTEMPT));
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter(rules);

    public LoginAttemptService() {
        super();
        int VERY_BIG_NUMBER = Integer.MAX_VALUE;

    }

    public void loginSucceeded(String key) {
        limiter.overLimitWhenIncremented(key, 0);
    }

    public void loginFailed(String key) {
        limiter.overLimitWhenIncremented(key);
    }

    public boolean isBlocked(String key) {
        return limiter.overLimitWhenIncremented(key);
    }
}
