package monitoring_apps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import koneksi.KoneksiDb;

public class UserSession {
    private static int userId = 1;
    private static String userName = "Dimas Aditya Pratama";
    private static String roleName = "Div. Administration";
    private static String jabatan = "Staff";
    private static String divisi = "Administration";
    private static String noTelp = "";
    private static String alamat = "";
    private static String email = "";

    // Getters and Setters
    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int id) {
        userId = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            userName = name.trim();
        }
    }

    public static String getRoleName() {
        return roleName;
    }

    public static void setRoleName(String role) {
        if (role != null && !role.trim().isEmpty()) {
            roleName = role.trim();
        }
    }

    public static String getJabatan() {
        return jabatan;
    }

    public static void setJabatan(String job) {
        if (job != null) {
            jabatan = job.trim();
        }
    }

    public static String getDivisi() {
        return divisi;
    }

    public static void setDivisi(String div) {
        if (div != null) {
            divisi = div.trim();
        }
    }

    public static String getNoTelp() {
        return noTelp;
    }

    public static void setNoTelp(String phone) {
        if (phone != null) {
            noTelp = phone.trim();
        }
    }

    public static String getAlamat() {
        return alamat;
    }

    public static void setAlamat(String addr) {
        if (addr != null) {
            alamat = addr.trim();
        }
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String mail) {
        if (mail != null) {
            email = mail.trim();
        }
    }

    /**
     * Helper to get the full profile of the currently logged-in user dynamically from the database.
     * Can be called from anywhere.
     */
    public static Map<String, Object> getActiveUserProfile() {
        Map<String, Object> profile = new HashMap<>();
        String sql = "SELECT id, nama, jabatan, divisi, no_telp, alamat, email, created_at FROM user WHERE id = ?";
        try (
            Connection conn = new KoneksiDb().connect();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profile.put("id", rs.getInt("id"));
                    profile.put("nama", rs.getString("nama"));
                    profile.put("jabatan", rs.getString("jabatan"));
                    profile.put("divisi", rs.getString("divisi"));
                    profile.put("no_telp", rs.getString("no_telp"));
                    profile.put("alamat", rs.getString("alamat"));
                    profile.put("email", rs.getString("email"));
                    profile.put("created_at", rs.getTimestamp("created_at"));
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal mengambil data profil aktif: " + e.getMessage());
        }
        return profile;
    }
}
