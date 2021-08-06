package pl.damian.shop.domain.dao;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.IOException;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Portable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private String type;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Category category;


    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writePortable(PortableWriter portableWriter) throws IOException {
        portableWriter.writeLong("id", id);
        portableWriter.writeString("name", name);
        portableWriter.writeString("brand", brand);
        portableWriter.writeString("type", type);
        portableWriter.writeDouble("price", price);
        portableWriter.writeInt("quantity", quantity);
//        portableWriter.writeUTF("category",category.toString());
    }

    @Override
    public void readPortable(PortableReader portableReader) throws IOException {
        id = portableReader.readLong("id");
        name = portableReader.readString("name");
        brand = portableReader.readString("brand");
        type = portableReader.readString("type");
        price = portableReader.readDouble("price");
        quantity = portableReader.readInt("quantity");
    }
}
