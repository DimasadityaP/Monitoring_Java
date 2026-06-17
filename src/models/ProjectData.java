package models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProjectData {
    public String id;
    public String nama;
    public String ta;
    public String subPerusahaan;
    public String jenis;
    public String instansi;
    public String tglMulai;
    public String tglSelesai;
    public BigDecimal nominal;
    public String status;
    public String userIdCreated;
    public String approverId;
    public String createdAt;
    public String modifyAt;
    public List<ProjectItem> items;

    public ProjectData() {
        this.items = new ArrayList<>();
    }

    public void addItem(ProjectItem item) {
        this.items.add(item);
    }

    public static class ProjectItem {
        public String projectId;
        public int itemId;
        public String namaPic;

        public ProjectItem() {
        }

        public ProjectItem(String projectId, int itemId, String namaPic) {
            this.projectId = projectId;
            this.itemId = itemId;
            this.namaPic = namaPic;
        }
    }
}
