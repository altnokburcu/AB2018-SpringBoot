package tr.com.burcualtinok.blog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "ENTRY")
public class Entry {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(name="TITLE")
    private String title;

    @NotEmpty
    @Column(name="BODY")
    private String body;

    @NotNull
    @Column(name = "CREATED_DATE")
    private LocalDate createdDate= LocalDate.now();

    @NotEmpty
    @Column(name="WRITER")
    private String writer;

    @Column(name="IS_DELETED")
    private  Boolean isDeleted;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "LIKE_ID")
    List<Like> likes = new ArrayList<>();


}
