package id.ac.umn.projectuts;

public class UserLogin {

    private boolean isLogin;
    private static UserLogin userLogin;

    public static UserLogin getInstance(){
        if(userLogin == null) userLogin = new UserLogin();
        return userLogin;
    }

    public UserLogin(){
        isLogin = false;
    }

    public void userLogin(){
        this.isLogin = true;
    }

    public void userLogOff(){
        this.isLogin = false;
    }

    public boolean getUserLogin(){
        return this.isLogin;
    }
}

