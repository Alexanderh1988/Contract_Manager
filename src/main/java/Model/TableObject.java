package Model;

public class TableObject {

    String Id;
    String DocumentName;
    String Text;
    String keyWord;
    Integer page;


    public TableObject(String Id, String DocumentName, String Text, String keyWord, Integer page) {
        super();
        this.Id = Id;
        this.DocumentName = DocumentName;
        this.Text = Text;
        this.keyWord = keyWord;
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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
