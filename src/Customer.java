

import java.text.SimpleDateFormat;

import java.util.Date;

public class Customer {
        private String name;
        private String id;
        private String PhoneNumber;

        public Customer(String name, String id,String PhoneNumber) {
            this.name = name;
            this.id = id;
            this.PhoneNumber = PhoneNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
}
