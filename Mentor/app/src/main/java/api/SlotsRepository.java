package api;

public class SlotsRepository {
    public static SlotsService getSlotsService() {
        return APIClient.getClient().create(SlotsService.class);
    }
}
