var selectedPizza = {};
var date = null;

$(function(){
    getPizzaList();
});

document.getElementById("myLink").click();

function openMenu(evt, menuName) {
    var i, x, tablinks;
    x = document.getElementsByClassName("menu");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablink");
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-red", "");
    }
    document.getElementById(menuName).style.display = "block";
    evt.currentTarget.firstElementChild.className += " w3-red";
}

function getPizzaList(){
    $.ajax({
        url: "http://localhost:8080/api/pizza/all",
        type: "GET",
        success: function(data){
            var text = "";
            $(data).each(function(index, item){
                text += "<h1><b>" + item.name + "</b> <span class='w3-right w3-tag w3-dark-grey w3-round'>RM" + item.price.toFixed(2) + "</span></h1>" +
                "<p class='w3-text-grey'>" +
                    item.description +
                    "<span class='w3-right w3-tag w3-red w3-round cartBtn' onclick=\"addToCart('" + 
                        item.id + "', '" + 
                        item.name + "', '" + 
                        item.description + "', '" + 
                        item.price.toFixed(2) + "')\">buy!</span>" +
                "</p>";
            })
            $('#Pizza').html(text);
        },
        error: function(req, error){
            alert(error);
        }
    });
}

function addToCart(id, name, description, price){
    alert("Pizza added to the cart!");
    selectedPizza = {
        "id": id,
        "name": name,
        "description": description,
        "price": price
    };
    date = new Date();
    date.setMinutes( date.getMinutes() + 30 );
}

function sendFeedback(){
    var testData = {comment: $("#resFeedback").val()};

    $.ajax({
        url: "http://localhost:8080/api/feedback/add",
        type: "POST",
        cors: true ,
        contentType:'application/json',
        secure: true,
        headers: {
          'Access-Control-Allow-Origin': '*',
        },
        data: JSON.stringify(testData),
        success: function(data){
            alert("Feedback Sent!");
        },
        error: function(req, error){
            alert(error);
        }
    });
}

function closeFeedbackModal(){
    $("#feedbackModal").hide();
}

function showFeedbackModal(){
    viewAllFeedback();
    $("#feedbackModal").show();
}

function viewAllFeedback(){
    $.ajax({
        url: "http://localhost:8080/api/feedback/all",
        type: "GET",
        success: function(data){
            var text = "";
            $(data).each(function(index, item){
                text += "<p class='w3-text-grey'>" + item.comment + "</p><hr>";
            })
            $('#feedbackDiv').html(text);
        },
        error: function(req, error){
            alert(error);
        }
    });
}

function closeCartModal(){
    $("#cartModal").hide();
}

function showCartModal(){
    $("#cartModal").show();
    viewCart();
}

var cartModal = document.getElementById("cartModal");
var feedbackModal = document.getElementById("feedbackModal");
var orderModal = document.getElementById("orderModal");
window.onclick = function(event) {
    if (event.target == modal) {
        cartModal.style.display = "none";
        feedbackModal.style.display = "none";
        orderModal.style.display = "none";
    }
}

function viewCart(){
    var text = "";
    if(jQuery.isEmptyObject(selectedPizza)){
        text += "<h1><b class='w3-text-grey'>No Pizza Selected</b></h1>";
    }
    else{
        text += "<h1><b>" + selectedPizza.name + "</b> <span class='w3-right w3-tag w3-dark-grey w3-round'>RM" + selectedPizza.price + "</span></h1>" +
            "<p class='w3-text-grey'>" + selectedPizza.description + "</p>" +
            "<h4 style='font-weight: 700'>Estimated Time: " + generateDateString(date) + " </h4>" + 
            "<h4 style='font-weight: 700'>Phone number:</h4>" + 
            "<input style='font-weight: 700' type='text' class='w3-input w3-padding-16 w3-border' placeholder='Phone number' id='checkoutPhone'>" + 
            "<span style='margin-top: 10px; font-size: 30px;' class='w3-right w3-tag w3-red w3-round cartBtn' onclick='checkoutCart(); return false;'>CHECKOUT</span>";
            $("#orderTime").html("asdsadsad");
    }
    
    $('#cartDiv').html(text);
}

function checkoutCart(){
    var phone = $("#checkoutPhone").val()
    if(phone == ""){
        alert("Please enter phone number.");
        return false;
    }

    var testData = {pizza_id: selectedPizza.id, total: selectedPizza.price, phone: phone, est_time: date};

    $.ajax({
        url: "http://localhost:8080/api/cart/add",
        type: "POST",
        cors: true ,
        contentType:'application/json',
        secure: true,
        headers: {
          'Access-Control-Allow-Origin': '*',
        },
        data: JSON.stringify(testData),
        success: function(data){
            alert("Cart checkout!");
            $("#cartModal").hide();
            selectedPizza = {};
            dateString = "";

            alert("Your Cart ID is " + data); // Akmal data tu no ID yg baru save
        },
        error: function(req, error){
            alert(error);
        }
    });   
}

function closeOrderModal(){
    $("#orderModal").hide();
}

function showOrderModal(){
    $("#orderIDView").val("");
    $("#orderModal").show();
    viewOrder();
}

function viewOrder(){
    var text = "";
    var orderID = $("#orderIDView").val();
    if(orderID == ""){
        text += "<h1><b class='w3-text-grey'>No Order</b></h1>";
    }
    else{
        $.ajax({
            url: "http://localhost:8080/api/cart/" + orderID,
            type: "GET",
            success: function(data){
                var cart = data.cart;
                var pizza = data.pizza;

                var text = "";
                text += "<h1><b>" + pizza.name + "</b> <span class='w3-right w3-tag w3-dark-grey w3-round'>RM" + pizza.price.toFixed(2) + "</span></h1>" +
                "<p class='w3-text-grey'>" + pizza.description + "</p>" +
                "<h4 style='font-weight: 700'>Estimated Time: " + generateDateString(new Date(cart.est_time)) + " </h4>" + 
                "<h4 style='font-weight: 700'>Phone number: " + cart.phone + " </h4>";
                // + "<span style='margin-top: 10px; font-size: 15px;' class='w3-right w3-tag w3-red w3-round cartBtn' onclick='deleteOrder(); return false;'>DELETE ORDER</span>";

                $('#orderDiv').html(text);
            },
            error: function(req, error){
                var text = "";
                text += "<h1><b class='w3-text-grey'>Error! No Order Found</b></h1>";
                $('#orderDiv').html(text);
            }
        });
    }
}

function generateDateString(date){
    dateString =
        date.getFullYear() + "/" +
        ("0" + (date.getMonth()+1)).slice(-2) + "/" +
        ("0" + date.getDate()).slice(-2) + " " +
        ("0" + date.getHours()).slice(-2) + ":" +
        ("0" + date.getMinutes()).slice(-2) + ":" +
        ("0" + date.getSeconds()).slice(-2);

    return dateString;
}