package com.example.encryptedmessagingservice;

public class Validation {

        public static boolean isValidEmail(String email) {
            return email.contains("@");
        }

        public static boolean isValidPassword(String password) {
            // Check password length
            if (password.length() < 8 || password.length() > 32) {
                return false;
            }

            // Check for at least one uppercase letter
            if (!password.matches(".*[A-Z].*")) {
                return false;
            }

            // Check for at least one lowercase letter
            if (!password.matches(".*[a-z].*")) {
                return false;
            }

            // Check for at least one digit
            if (!password.matches(".*\\d.*")) {
                return false;
            }

            // Check for at least one special character
            if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
                return false;
            }

            return true;
        }
    }

