package demo.yang.domain.system;

public class AuthorBean {
    private String id;
    private String pId;
    private String name;
    private String checked;

    public AuthorBean() {
    }

    public AuthorBean(String id, String pId, String name, String checked) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
