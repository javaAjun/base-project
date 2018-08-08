//动态增加和删除表格行的内容
      document.getElementById("addID").onclick=function(){
     
       
        //判断
        
            //创建tr元素
            var trElemnet = document.createElement("tr");
            //创建td元素
            var td1Element = document.createElement("td");
            var td2Element = document.createElement("td");
            var td3Element = document.createElement("td");
            //将用户名和邮箱添加到td元素
            td1Element.innerHTML = username;
            td2Element.innerHTML = email;
            //创建按钮
            var delElement = document.createElement("input");
            delElement.type="button";
            delElement.value="删除";
            //为按钮添加单击事件
            delElement.onclick=function(){
                //删除按钮所在的tr对象
                trElemnet.parentNode.removeChild(trElemnet);                
            }
            td3Element.appendChild(delElement);
            //将td元素添加到tr元素中
            trElemnet.appendChild(td1Element);
            trElemnet.appendChild(td2Element);
            trElemnet.appendChild(td3Element);
            //将tr元素添加到tbody元素中
            document.getElementById("tbodyID").appendChild(trElemnet);
            //清空文本框中的值
            document.getElementById("usernameID").value="";
            document.getElementById("emailID").value="";
        
      }