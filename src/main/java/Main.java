public class Main {
    public static void main(String[] args) {
        boolean valid = validateUSAPhoneNumber("(212)345-6789");
        System.out.println(valid?"Valid":"INvalid");

    }

    private static boolean validateUSAPhoneNumberWithRegex(String phone) {
        return phone.matches("\\(\\d{3}\\)\\d{3}-\\d{4}");
    }

    private static boolean validateUSAPhoneNumber(String phone) {
        phone = phone.trim(); //.trim() -
        boolean valid = phone.length() == 13;
        for(int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);

            switch (i) {
                case 0:
                    valid = c == '(';
                    break;
                case 4:
                    valid = c == ')';
                    break;
                case 8:
                    valid = c =='-';
                    break;
                default:
                    valid = c >= '0' && c <= '9';
            }
            if (!valid) {
                break;
            }
        }
        return valid;

    }
}
