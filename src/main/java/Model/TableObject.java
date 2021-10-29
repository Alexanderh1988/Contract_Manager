package Model;

public class TableObject {

    String Id;
    String DocumentName;
    String Text;
    Integer page;

    public TableObject(String Id, String DocumentName, String Text, Integer page) {
        super();
        this.Id = Id;
        this.DocumentName = DocumentName;
        this.Text = Text;
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public String getText() {
        return Text;
    }
}
