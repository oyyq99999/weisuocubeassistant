package scramble;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Form;

//import util.TwoPhaseSearch;
import cs.min2phase.Search;
import cs.min2phase.Tools;

public class _333Scramble extends Scramble {

    private Form prepare = null;

    public _333Scramble() {
        this(25);
    }

    public _333Scramble(int maxLength) {
        // 最大长度
        this.length = maxLength;
    }

    public _333Scramble(int maxLength, Form prepare) {
        this(maxLength);
        this.prepare = prepare;
    }

    public String scramble() {
        if (!Tools.inited) {
            // System.out.println("Initialization");
            try {
                FileConnection file;
                file = (FileConnection) Connector.open(
                        "file:///e:/WCA/twophase.data", Connector.READ);
                DataInputStream dis;
                if (file.exists()
                        && Tools.initFrom(dis = file.openDataInputStream())) {
                    dis.close();
                } else {
                    file = (FileConnection) Connector.open("file:///e:/WCA/");
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    file = (FileConnection) Connector
                            .open("file:///e:/WCA/twophase.data");
                    if (file.exists())
                        file.delete();
                    file.create();
                    DataOutputStream dos;
                    Tools.initTo(dos = file.openDataOutputStream());
                    dos.flush();
                    dos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // TODO Auto-generated method stub
        scrambleSequence = new Search().solution(Tools.randomCube(), length,
                10000, false, true);
        return scrambleSequence;
    }

}
