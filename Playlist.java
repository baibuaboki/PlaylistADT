import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Playlist — ADT แทนรายการเพลงที่ผู้ใช้จัดลำดับไว้
 *
 * ค่านามธรรม (A): ลำดับของเพลง เช่น [เพลงA, เพลงB, เพลงC]
 *
 * ตัวอย่างการใช้งาน:
 *     Playlist p = new Playlist();
 *     p.add("Bohemian Rhapsody");
 *     p.add("Imagine");
 *     System.out.println(p.size());   // 2
 */
public class Playlist {

    public static final int MAX_SONGS = 100;

    // ===== representation =====
    // TODO 1: เขียน Abstraction Function ตรงนี้
    // Abstraction Function:
    //   AF(songs) = เพลย์ลิสต์ที่เล่นเพลง songs.get(0), songs.get(1), ... ตามลำดับ

    // TODO 2: เขียน Representation Invariant ตรงนี้ (4 ข้อ)
    // Representation Invariant:
    //   songs != null
    //   songs ในลิสต์ทุกตัวจะไม่มีตัวไหนเป็น null
    //   songs != " "
    //   songs จะไม่มีตัวไหนที่ซ้ำกัน
    //   songs ทั้งหมดมีได้ไม่เกิน 100 ตัว

    // TODO 3: เขียน Safety from rep exposure ตรงนี้
    // Safety from rep exposure:
    //   field songs เป็น private final และ object จะ copy ตอนส่งออกและรับเข้า

    private final List<String> songs;


    /**
     * TODO 4: เขียน checkRep()
     * แปลง RI ทุกข้อเป็น assert หนึ่งบรรทัด พร้อมข้อความอธิบาย
     */
    private void checkRep() {
        assert songs != null || songs.size() <= MAX_SONGS;

        Set<String> seen = new HashSet<>();
        for (String s : songs) {
            assert s != null;
            assert !(s=="");
            assert seen.add(s) : "ชื่อเพลงซ้ำ: " + s;
        }
    }

    // ===== Creator =====

    /**
     * สร้างเพลย์ลิสต์ว่าง
     */
    public Playlist() {
        this.songs = new ArrayList<>();
        checkRep();
    }

    /**
     * TODO 5: Creator ตัวที่สอง
     * สร้างเพลย์ลิสต์จากรายชื่อเพลงที่ให้มา
     *
     * ระวัง: ห้ามเก็บ reference ของ initial ตรง ๆ (rep exposure!)
     *
     * @param initial รายชื่อเพลงเริ่มต้น ต้องไม่ซ้ำและไม่เกิน MAX_SONGS
     * @throws IllegalArgumentException ถ้า initial ผิดเงื่อนไข
     */
    public Playlist(List<String> initial) {
        
        if (initial == null) throw new IllegalArgumentException();
        if(initial.size() > MAX_SONGS) throw new IllegalArgumentException();
        

        Set<String> seen = new HashSet<>();
        for (String s : initial) {
            if(s == null || s == "") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
        
       this.songs = new ArrayList<>(initial);   // แก้บรรทัดนี้
       checkRep();
    }

    // ===== Mutators =====

    /**
     * TODO 6: เพิ่มเพลงต่อท้ายเพลย์ลิสต์
     *
     * @param song ชื่อเพลง ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีเพลงนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า song เป็น null หรือสตริงว่าง
     */
    public boolean add(String song) {

        if(song == null || song == "") throw new IllegalArgumentException();
        if(songs.contains(song) || songs.size() >= MAX_SONGS) return false;

        songs.add(song);
        checkRep();

        return true;   // แก้บรรทัดนี้
    }

    /**
     * TODO 7: ลบเพลงออกจากเพลย์ลิสต์
     *
     * @param song ชื่อเพลงที่ต้องการลบ
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบเพลงนี้
     */
    public boolean remove(String song) {
        if(!songs.contains(song)) return false;

        songs.remove(song);
        checkRep();

        return true;   // แก้บรรทัดนี้
    }

    // ===== Observers =====

    /**
     * TODO 8: คืนจำนวนเพลงในเพลย์ลิสต์
     */
    public int size() {
        return songs.size();   // แก้บรรทัดนี้
    }

    /**
     * TODO 9: ตรวจว่ามีเพลงนี้อยู่หรือไม่
     */
    public boolean contains(String song) {

        return songs.contains(song);

    }

    /**
     * TODO 10: คืนรายชื่อเพลงทั้งหมดตามลำดับ
     *
     * ระวัง: ห้ามคืน reference ของ songs ตรง ๆ (rep exposure!)
     */
    public List<String> songs() {
        
        return new ArrayList<>(songs);   // แก้บรรทัดนี้

    }

    // ===== Producer =====

    /**
     * TODO 11: คืนเพลย์ลิสต์ใหม่ที่มีเพลงเดียวกันแต่สลับลำดับ
     *
     * ระวัง: ห้ามแก้เพลย์ลิสต์เดิม (this) เด็ดขาด
     *
     * @return เพลย์ลิสต์ใหม่ที่สลับลำดับแล้ว
     */
    public Playlist shuffled() {

        List<String> copy = new ArrayList<>(songs);
        Collections.shuffle(copy);


        return new Playlist(copy);   // แก้บรรทัดนี้
    }

    @Override
    public String toString() {
        return songs.toString();
    }
}
