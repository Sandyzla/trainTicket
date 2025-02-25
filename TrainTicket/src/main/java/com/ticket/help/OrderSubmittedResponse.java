package com.ticket.help;

/**
 * code:{'success':成功执行, 'fail':某一记录不合法, 'busy':资源占用, 'empty':空请求, 'no_ticket':无足够余票}
 * index仅在code='fail生效,此时表示错误的记录索引
 * message:供前端显示的错误信息
 */
public class OrderSubmittedResponse {
   //   返回码：
   private String code;
   //   code=fail时出错的订单记录索引，若code=fail 且index=-1，则表示query为空,若code=success或busy则index没有具体含义
   private int index;
   private String message;

   public OrderSubmittedResponse(String code) {
      this.code = code;
   }

   public OrderSubmittedResponse(String code, int index) {
      this.code = code;
      this.index = index;
   }

   public OrderSubmittedResponse(String code, String message) {
      this.code = code;
      this.message = message;
   }

   public OrderSubmittedResponse(String code, int index, String message) {
      this.code = code;
      this.index = index;
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public int getIndex() {
      return index;
   }

   public void setIndex(int index) {
      this.index = index;
   }

}
