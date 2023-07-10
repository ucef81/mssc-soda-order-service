package org.mssc.order.msscbeerorderservice.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

   public BaseEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate){
      this.id =id;
      this.version = version;
      this.createdDate = createdDate;
      this.lastModifiedDate = lastModifiedDate;
   }


   @Id
   @GeneratedValue(generator = "uuid4")
   @GenericGenerator(name = "uuid4", strategy = "org.hibernate.id.UUIDGenerator")
   @Type(type="org.hibernate.type.UUIDCharType")
   @Column(columnDefinition = "varchar(36)",updatable = false, nullable = false)
   private UUID id;

   @Version
   private Long version;

   @CreationTimestamp
   @Column(updatable = false)
   private Timestamp createdDate;

   @UpdateTimestamp
   private Timestamp lastModifiedDate;


   public boolean isNew(){
      return this.id == null;
   }

}
