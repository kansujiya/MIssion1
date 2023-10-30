package ArrayString;

    public class Main {

        // barcode checksum
        // https://www.gs1.org/docs/barcodes/GS1_General_Specifications.pdf section 7.9.2
        // ...

        public static void main(String[] args) {
            System.out.println("============= barcode checksum variable length ================");
            System.out.println("https://www.gs1.org/docs/barcodes/GS1_General_Specifications.pdf section 7.9.2");

            int[][] f = {
                    {0, 2, 4, 6, 8, 9, 1, 3, 5, 7},
                    {0, 3, 6, 9, 2, 5, 8, 1, 4, 7},
                    {0, 5, 1, 6, 2, 7, 3, 8, 4, 9},
                    {0, 5, 9, 4, 8, 3, 7, 2, 6, 1}
            };

            int[][] lookupTables = {
                    {0, 0, 1, 3},
                    {2, 0, 3, 2, 0}
            };

            // show some examples
            System.out.println(calculateCheckDigit("6774"));
            System.out.println(calculateCheckDigit("4414"));
        }

        public static String calculateCheckDigit(String gtin) {
            int[] gtinArray = new int[gtin.length()];

            for (int i = 0; i < gtin.length(); i++) {
                gtinArray[i] = Character.getNumericValue(gtin.charAt(i));
            }

            int sum = 0;
            for (int i = gtinArray.length - 2; i >= 0; i -= 2) {
                sum += gtinArray[i] * 3;
            }

            int checkDigit = (10 - (sum % 10)) % 10;
            return gtin + checkDigit;
        }

    }

