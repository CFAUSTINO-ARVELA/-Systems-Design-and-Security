class Account {

    private String title;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String clearance;

    Account(String _title, String _forname, String _password, String _surname, String _clearance) {
        this.title = _title;
        this.forename = _forname;
        this.surname = _surname;
        this.clearance = _clearance;
    }

    private void createAccount() {

    }

    private String getUsername() {
        // generate username based on details
        //this needs to be split later into get and generate username so
        // we don't generate it everytime
        return "PlaceHolderUsername123";
    }

    private void generateEmail() {
        // generate email based on name and number
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle() {

    }

    public String getForename() {
        return this.forename;
    }

    public void setForename() {

    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname() {

    }

    public String getClearance() {
        return this.clearance;
    }

    
}