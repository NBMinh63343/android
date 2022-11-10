package api;

public class TestRepository {
    public static TestService getTestService() {
        return APIClient.getClient().create(TestService.class);
    }
}
