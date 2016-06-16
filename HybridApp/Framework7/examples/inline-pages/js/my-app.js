// Initialize your app
var myApp = new Framework7({
    animateNavBackIcon:true
});


// Export selectors engine
var $$ = Dom7;

var myPhotoBrowserStandalone = myApp.photoBrowser({
    photos : [
        'https://farm9.staticflickr.com/8645/16031539611_b45d2bbe57_z.jpg',
        'https://farm4.staticflickr.com/3933/15296234239_4f11d889a8_z.jpg',
        'https://farm3.staticflickr.com/2949/15296430490_3366e6f050_z.jpg',
        'https://farm4.staticflickr.com/3937/15483139575_c6eced4510_z.jpg',
    ]
});

$$('.viewGallery').on('click', function () {
    myPhotoBrowserStandalone.open();
});


// Add main View
var mainView = myApp.addView('.view-main', {
    // Enable dynamic Navbar
    dynamicNavbar: true,
    // Enable Dom Cache so we can use all inline pages
    domCache: true
});
