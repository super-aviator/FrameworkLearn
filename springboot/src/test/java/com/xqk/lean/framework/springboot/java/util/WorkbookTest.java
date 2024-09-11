package com.xqk.lean.framework.springboot.java.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Workbook test.
 */
public class WorkbookTest {
    /**
     * Test.
     *
     * @throws WriteException the write exception
     * @throws IOException    the io exception
     */
    @Test
    public void test() throws WriteException, IOException {
        //这里为导出文件存放的路径
        String filePath = "E:\\Hello" + "\\";
        //加入一个uuid随机数是因为
        //每次导出的时候，如果文件存在了，会将其覆盖掉，这里是保存所有的文件

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        // 给要导出的文件起名为 "测试导出数据表_时间.xls"
        String filePath2 = filePath + "测试导出数据表" + "_" + fmt.format(new Date()) + ".xls";

        File file = new File(filePath2);
        if (!file.exists()) {
            file.createNewFile();
        }
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(file);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet", 0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        Label xuexiao = new Label(0, 0, "学校");
        sheet.addCell(xuexiao);
        Label zhuanye = new Label(1, 0, "专业");
        sheet.addCell(zhuanye);
        Label jingzhengli = new Label(2, 0, "专业竞争力");
        sheet.addCell(jingzhengli);

        Label qinghua = new Label(0, 1, "清华大学");
        sheet.addCell(qinghua);
        Label jisuanji = new Label(1, 1, "计算机专业");
        sheet.addCell(jisuanji);
        Label gao = new Label(2, 1, "高");
        sheet.addCell(gao);

        Label beida = new Label(0, 2, "北京大学");
        sheet.addCell(beida);
        Label falv = new Label(1, 2, "法律专业");
        sheet.addCell(falv);
        Label zhong = new Label(2, 2, "中");
        sheet.addCell(zhong);

        Label ligong = new Label(0, 3, "北京理工大学");
        sheet.addCell(ligong);
        Label hangkong = new Label(1, 3, "航空专业");
        sheet.addCell(hangkong);
        Label di = new Label(2, 3, "低");
        sheet.addCell(di);

        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
    }
}