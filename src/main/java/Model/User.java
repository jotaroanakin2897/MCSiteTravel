package Model;

public class User
{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String username;
    private Email email;
    private String password;

    public User(String username,Email email, String password)
    {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public User(int id,String username,Email email)
    {
        this.id=id;
        this.username=username;
        this.email=email;
    }

    public User() {

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class UserBuilder
    {
        private String username;
        private Email email;
        private String password;


        public UserBuilder(String username,String password)
        {
            this.username=username;
            this.password=password;
        }


        public UserBuilder(Email email, String password)
        {
            this.email=email;
            this.password=password;

        }


        public User buildEmail(Email email, String password) {
            User user =  new User();
            user.setEmail(email);
            user.setPassword(password);
            validateUserObjectByEmail(user);
            return user;
        }


        public User buildUser(String user,String password) {
            User userObj =  new User();
            userObj.setUsername(user);
            userObj.setPassword(password);
            return userObj;
        }


        private void validateUserObjectByEmail(User user) {
            //check se la mail è valida
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Email getEmail() {
            return email;
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }


}
