package springcloud.dao;

import com.whwxuexi.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springcloud.entity.Menu;

import java.util.List;

/**
 * @auther zzyy
 * @create 2020-01-27 20:21
 */
@Mapper  //import org.apache.ibatis.annotations.Mapper;
public interface PaymentDao
{
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

    public List<Payment> getPaymentByList();

    List<Menu> findTree();
}
