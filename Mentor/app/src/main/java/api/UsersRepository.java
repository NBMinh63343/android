package api;

public class UsersRepository {
    public static UsersService getUsersService() {
        return APIClient.getClient().create(UsersService.class);
    }
}
