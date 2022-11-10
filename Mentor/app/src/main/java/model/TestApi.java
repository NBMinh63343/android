package model;

public class TestApi {
    String rootPath;

    public TestApi(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {

        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
