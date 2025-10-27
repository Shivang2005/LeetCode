class Solution {
    public int numberOfBeams(String[] bank) {
        int totalBeams = 0;
        int prevDeviceCount = 0;

        for (String row : bank) {
            int currentDeviceCount = 0;
            for (char c : row.toCharArray()) {
                if (c == '1') {
                    currentDeviceCount++;
                }
            }

            if (currentDeviceCount > 0) {
                totalBeams += (prevDeviceCount * currentDeviceCount);
                prevDeviceCount = currentDeviceCount;
            }
        }

        return totalBeams;
    }
}