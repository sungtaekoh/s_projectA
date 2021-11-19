
function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
        	// R : 도로명, J : 지번
           console.log("data.userSelectedType : "+data.userSelectedType)
           console.log("data.roadAddress : "+data.roadAddress)
           console.log("data.jibunAddress : "+data.jibunAddress)
           console.log("data.zonecode(우편번호) : "+data.zonecode)
           var addr=""
           if(data.userSelectedType === 'R'){
        	   addr = data.roadAddress
           }else{
        	   addr = data.jibunAddress
           }
           $("#addr1").val(data.zonecode)
           $("#addr2").val(addr)
           $("#addr3").focus()
        }
    }).open();
}