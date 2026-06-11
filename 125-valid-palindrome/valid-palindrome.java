class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            
            // Move the left pointer forward if the current character is not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            
            // Move the right pointer backward if the current character is not alphanumeric
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            
            // Compare the characters at both pointers after converting them to lowercase
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // If they don't match, it's not a palindrome
            }
            
            // Move both pointers inward for the next iteration
            left++;
            right--;
        }
        
        // If the pointers cross without any mismatch, the string is a valid palindrome
        return true;
    }
}