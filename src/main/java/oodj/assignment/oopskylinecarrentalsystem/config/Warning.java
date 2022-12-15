package oodj.assignment.oopskylinecarrentalsystem.config;

public final class Warning {

    public static final String FILLINALLFIELDS = "Please fill in all the fields.";

    public static final class USER {
        public static final String USERNAME = """
                            1. Username must be 8-15 characters long.
                            2. Can only contain alphanumeric characters and `-` or `_`.
                            3. Username must be unique.
                            """;

        public static final String NAME = "Name must be in the format of `Firstname Lastname`.";

        public static final String EMAIL = """
                            1. Email must be in the correct format.
                            2. Email must be unique.
                            """;

        public static final String IC = """
                            1. IC number must be in the format of `XXXXXX-XX-XXXX`.
                            2. IC number must be unique.
                            """;

        public static final String PHONENUMBER = """
                            1. Phone number must be in the format of `601X-XXXXXXX`.
                            2. Phone number must be unique.
                            """;

        public static final String POSTCODE = "Postcode must be in the format of `XXXXX`.";

        public static final String PASSWORD = """
                            1. Password must be 8-32 characters long
                            2. Must contain at least 1 uppercase letter
                            3. Must contain at least 1 lowercase letter
                            4. Must contain at least 1 number
                            5. Must contain at least 1 special character
                            """;

        public static final String UNMATCHEDPASSWORD = "Passwords do not match.";
    }


}