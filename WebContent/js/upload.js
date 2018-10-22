var feedback = function(res) {
    if (res.success === true) {
        var get_link = res.data.link.replace(/^http:\/\//i, 'https://');
        document.querySelector('.status').classList.add('bg-success');
        //document.querySelector('.status').innerHTML =
        //    'Image : ' + '<br><input class="image-url" type="hidden" name="imglink" value=\"' + get_link + '\"/>' + '<img class="img" alt="Imgur-Upload" src=\"' + get_link + '\"/>';
        console.log(get_link);
    }
};

new Imgur({
    clientid: '85a8ed4972795ea', //You can change this ClientID
    callback: feedback
});