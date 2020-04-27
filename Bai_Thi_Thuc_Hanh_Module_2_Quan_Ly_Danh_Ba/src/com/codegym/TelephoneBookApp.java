package com.codegym;

import java.util.*;
import java.io.*;

public class TelephoneBookApp {
    private LinkedList<Catalog> storageCatalog;
    private String storagePathBinary = "/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorageBinaryData.txt";
    private String storagePathFormatText = "/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorage.txt";
    private static Scanner sc;

    public TelephoneBookApp() {
        this.storageCatalog = new LinkedList<Catalog>();
    }

    public void showMenu() {
        System.out.println("---- Chương Trình Quản Lý Danh Bạ ------");
        System.out.println("1 - Xem Danh Sách");
        System.out.println("2 - Thêm Mới");
        System.out.println("3 - Cập Nhật");
        System.out.println("4 - Xóa");
        System.out.println("5 - Tìm Kiếm");
        System.out.println("6 - Đọc Tù File");
        System.out.println("7 - Ghi Vào File");
        System.out.println("8 - Thoát");
        System.out.println("Chọn Chức Năng");
    }

    public void chooseOption() {
        System.out.println("Hãy nhấn một số từ 1 - 8 để chọn chức nằng:");
        sc = new Scanner(System.in);
        int choosen = 0;

        if (sc.hasNextInt()) {
            choosen = sc.nextInt();
            if (choosen == 1) {
                showCatalog("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorage.txt");
            } else if (choosen == 2) {
                Catalog catalog = createNew();
                add(catalog);
                System.out.println("Danh bạ mới đã được thêm thành công vào trong file CatalogStorage.txt click vào file để xem chi tiết");
            } else if (choosen == 3) {
                update();
            } else if (choosen == 4) {
                delete();
            } else if (choosen == 5) {
                System.out.println("Hay nhập vào tên catalog để tìm kiếm:");
                Scanner sc = new Scanner(System.in);
                String nameToSearch = null;
                if (sc.hasNextLine()) {
                    nameToSearch = sc.nextLine();
                }
                findCatalog(nameToSearch);
            }else if (choosen == 6) {
                readFromFile("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorage.txt");
            }else if (choosen == 7) {
                writeToFile("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogExtractTable.txt");
            } else if (choosen == 8) {
                exit();
            } else {
                System.out.println("Không có chức năng này !!!");
            }
        } else {
            System.out.println("Định dạng chọn không đúng, hãy chọn lại");
            chooseOption();
        }
    }

    private Catalog createNew() {
        sc = new Scanner(System.in);
        System.out.println("Bạn muốn thêm mới danh bạ vậy hãy điền các thông tin sau:");
        System.out.println("Hãy nhập Số điện thoại:");
        String numberPhone = null;
        if (sc.hasNextLine()) {
            numberPhone = sc.nextLine();
        }
        System.out.println("Hãy nhập Nhóm:");
        String group = null;
        if (sc.hasNextLine()) {
            group = sc.nextLine();
        }
        System.out.println("Hãy nhập Họ Tên:");
        String fullname = null;
        if (sc.hasNextLine()) {
            fullname = sc.nextLine();
        }
        System.out.println("Hãy nhập Giới Tính:");
        String sex = null;
        if (sc.hasNextLine()) {
            sex = sc.nextLine();
        }
        System.out.println("Hãy nhập Địa Chỉ:");
        String address = null;
        if (sc.hasNextLine()) {
            address = sc.nextLine();
        }
        System.out.println("Hãy nhập Ngày Sinh:");
        String birthday = null;
        if (sc.hasNextLine()) {
            birthday = sc.nextLine();
        }
        System.out.println("Hãy nhập Email:");
        String email = null;
        if (sc.hasNextLine()) {
            email = sc.nextLine();
        }

        Catalog catalog = new Catalog(numberPhone , group , fullname, sex, address, birthday, email);

        return catalog;
    }

    public boolean add(Catalog item) {
        String sourceBinaryPath = "/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorageBinaryData.txt";
        boolean result = this.storageCatalog.add(item);
        writeToFileBinary();
        writeToFileFormat(sourceBinaryPath);

        return result;
    }

    private void writeToFileBinary() {
        File file = new File(storagePathBinary).getAbsoluteFile();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            try {
                for (Catalog item : this.storageCatalog) {
                    out.writeObject(item);
                }
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeToFileFormat(String targetPath) {
        File file = new File(targetPath).getAbsoluteFile();
        String sourceFormatPath = "/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorage.txt";
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            PrintWriter out = new PrintWriter(sourceFormatPath);
            try {
                out.format("%70s\n", "DANH SÁCH DANH BẠ");
                out.format("%5s %20s %20s %20s %10s %15s", "Id", "Số điên thoại", "Nhóm", "Họ Tên", "Giới Tính",
                        "Địa Chỉ\n");
                out.format(
                        "----------------------------------------------------------------------------------------------------------------------\n");
                for (int i = 0; i < this.storageCatalog.size(); i++) {
                    Catalog production = (Catalog) in.readObject();
                    out.format("%5s", production.getId());
                    out.format("%20s", production.getNumberPhone());
                    out.format("%30s", production.getGroup());
                    out.format("%25s", production.getFullName());
                    out.format("%17s", production.getSex());
                    out.format("%15s\n", production.getAddress());
                }
                out.format(
                        "----------------------------------------------------------------------------------------------------------------------");
            } finally {
                out.close();
                in.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public boolean findCatalog(String nameToSearch) {
        File file = new File("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogStorage.txt");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            PrintWriter out = new PrintWriter("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogExtractTable.txt");
            String line = null;
            try {
                int count = 0;
                while ((line = in.readLine()) != null) {
                    if (line.contains(nameToSearch)) {
                        out.println(line);
                        count++;
                    }
                }

                if (count > 0) {
                    System.out.println("Đã tìm thấy kết quả hãy mở file CatalogExtracTable.txt để thấy đc kết quả tìm kiếm");
                    return true;
                } else {
                    System.out.println("Không tìm thấy kết quả tìm kiếm");
                    return false;
                }
            }finally {
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public void showCatalog(String sourcePath) {
        File file = new File(sourcePath);
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            PrintWriter out = new PrintWriter("/root/Documents/Bai_Thi_Thuc_Hanh_Module_2_Quan_Ly_Danh_Ba/src/com/codegym/CatalogExtractTable.txt");
            String line = null;
            try {
                while ((line = in.readLine()) != null) {
                    out.println(line);
                }
            }finally {
                out.close();
            }
            System.out.println("Danh sach catalog đã được xử lý và hiển thị đầy đủ trong file CatalogExtractTable.txt, hãy mở file ra để đọc");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void update() {

    }

    private void delete() {

    }

    private void readFromFile(String sourcPath) {
        System.out.println("Chức năng đọc từ file đa được bao gồm trong chức naưng showCatalog()");
        showCatalog(sourcPath);
    }

    private void writeToFile(String sourcPath) {
        System.out.println("Chức năng viết vào file đa được bao gồm trong chức năng showCatalog()");
        showCatalog(sourcPath);
    }

    private void exit() {

    }
}
