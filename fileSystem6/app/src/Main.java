package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        GeneralManagement gm = new GeneralManagement();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bucket Management Menu ---");
            System.out.println("1. Create bucket");
            System.out.println("2. Upload file");
            System.out.println("3. Delete file");
            System.out.println("4. List objects");
            System.out.println("5. Delete bucket");
            System.out.println("6. List buckets");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int opcion;

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (Exception e) {
                System.out.println("Invalid input. You must enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue; // Show the menu again
            }

            switch (opcion) {
                case 1:
                    System.out.print("Username to generate bucket: ");
                    gm.setNameUser(scanner.nextLine());
                    gm.createBucket();
                    break;

                case 2:
                    System.out.print("Bucket name: ");
                    gm.setBucketName(scanner.nextLine());
                    System.out.print("Object name (name in GCS): ");
                    gm.setObjectName(scanner.nextLine());
                    System.out.print("Local file path: ");
                    gm.setFilePath(scanner.nextLine());
                    gm.uploadObject();
                    break;

                case 3:
                    System.out.print("Bucket name: ");
                    gm.setBucketName(scanner.nextLine());
                    System.out.print("Object name to delete: ");
                    gm.setObjectName(scanner.nextLine());
                    gm.deleteObject();
                    break;

                case 4:
                    System.out.print("Bucket name: ");
                    gm.setBucketName(scanner.nextLine());
                    gm.listObjects();
                    break;

                case 5:
                    System.out.print("Bucket name to delete: ");
                    gm.setBucketName(scanner.nextLine());
                    gm.deleteBucket();
                    break;

                case 6:
                    gm.listBuckets();
                    break;

                case 7:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}

