package DesignPatterns.Structural;

//This pattern help when OBJECT inside OBJECT
//like Delivery BOX
//like FILE SYSTEM
// like tree structure

//         File System(Delivery Box)
//  File(Item)                 Directory(Delivery Box)
//               File(Item)                  Directory(Delivery Box)
//                           File(Item)                    Directory(delivery Box)


import java.io.File;
import java.util.ArrayList;
import java.util.List;

interface FileSystem {
    void ls();
}

class FileSystemImpl implements FileSystem {
    String fileName;

    FileSystemImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ls() {
        System.out.println(this.fileName);
    }
}

class Directory implements FileSystem {

    List<FileSystem> files  = new ArrayList<>();

    void addFile(FileSystem file) {
        files.add(file);
    }

    @Override
    public void ls() {
        for(FileSystem file : files) {
            file.ls();
        }
    }
}

public class Composite {

    public static void main(String[] args) {

        Directory parentDir = new Directory();
        FileSystem f1 = new FileSystemImpl("A");
        parentDir.addFile(f1);

        Directory childDir = new Directory();
        FileSystem f2 = new FileSystemImpl("B");
        childDir.addFile(f2);

        parentDir.addFile(childDir);

        parentDir.ls();

    }

}
