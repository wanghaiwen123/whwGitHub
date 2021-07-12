
package springcloud.controller;

import com.whwxuexi.springcloud.entities.CommonResult;
import com.whwxuexi.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springcloud.dao.PaymentDao;
import springcloud.entity.Menu;
import springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @auther zzyy
 * @create 2020-01-27 21:17
 */
@RestController
@Slf4j
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private PaymentService paymentService;



    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment)
    {
        int result = paymentService.create(payment);
        log.info("*****插入操作返回结果:" + result);

        if(result > 0)
        {
            return new CommonResult(200,"插入成功,返回结果"+result+"\t 服务端口："+serverPort,payment);
        }else{
            return new CommonResult(444,"插入失败",null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果:{}",payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功"+"\t 服务端口："+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id,null);
        }
    }

    @GetMapping(value = "/payment/getList")
    public CommonResult<Payment> getPaymentByList()
    {
        List<Payment> payment = paymentService.getPaymentByList();
        log.info("*****查询结果:{}",payment);
        if (payment != null) {
            return new CommonResult(200,"查询成功"+"\t 服务端口："+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: ",null);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");
        // 创建一个并行流
        Stream<String> parallelStream = list.parallelStream();
        System.out.println(parallelStream);
        int[] array={1,3,5,6,8};
        IntStream stream = Arrays.stream(array);
        System.out.println(stream);
    }
    @GetMapping(value = "/payment/findTree")
    public CommonResult<Menu> sss(){

        log.info("*****查询结果:{}",findTree());
        return   new CommonResult(200,"查询成功"+"\t 服务端口："+serverPort,findTree());
    }
    /*
     * 排序,根据order排序
     */
    public Comparator<Menu> order(){
        Comparator<Menu> comparator = new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                if(o1.getOrder() != o2.getOrder()){
                    return o1.getOrder() - o2.getOrder();
                }
                return 0;
            }
        };
        return comparator;
    }

    public Map<String,Object> findTree(){
        Map<String,Object> data = new HashMap<String,Object>();
        try {//查询所有菜单
            List<Menu> allMenu = paymentDao.findTree();
            //根节点
            List<Menu> rootMenu = new ArrayList<Menu>();
            for (Menu nav : allMenu) {
                if(nav.getParentId().equals("0")){//父节点是0的，为根节点。
                    rootMenu.add(nav);
                }
            }
            /* 根据Menu类的order排序 */
            Collections.sort(rootMenu, order());
            //为根菜单设置子菜单，getClild是递归调用的
            for (Menu nav : rootMenu) {
                /* 获取根节点下的所有子节点 使用getChild方法*/
                List<Menu> childList = getChild(nav.getId(), allMenu);
                nav.setChildren(childList);//给根节点设置子节点
            }
            /**
             * 输出构建好的菜单数据。
             *
             */
            data.put("success", "true");
            data.put("list", rootMenu);
            return data;
        } catch (Exception e) {
            data.put("success", "false");
            data.put("list", new ArrayList());
            return data;
        }
    }
//    获取子菜单：

    /**
     * 获取子节点
     * @param id 父节点id
     * @param allMenu 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<Menu> getChild(String id,List<Menu> allMenu){
        //子菜单
        List<Menu> childList = new ArrayList<Menu>();
        for (Menu nav : allMenu) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(nav.getParentId().equals(id)){
                childList.add(nav);
            }
        }
        //递归
        for (Menu nav : childList) {
            nav.setChildren(getChild(nav.getId(), allMenu));
        }
        Collections.sort(childList,order());//排序
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<Menu>();
        }
        System.out.println(childList);
        return childList;
    }
}



