package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * После добавления работы БД этот класс не нужен.
 */

public class AccountService {
    private final Map<String, Users> loginToProfile;
    private final Map<String, Users> sessionIdToProfile;

    public AccountService() {
        loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
    }

    public void addNewUser(Users users) {
        loginToProfile.put(users.getLogin(), users);
    }

    public Users getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public Users getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, Users users) {
        sessionIdToProfile.put(sessionId, users);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
