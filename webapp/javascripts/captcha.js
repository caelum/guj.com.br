function reloadCaptcha(id) {
    var captcha_id = id || "captcha";
    var challenge = Math.ceil(Math.random() * 1000000000000000) + 'guj';
    alert("a imagem será recarregada, aguarde");
    $(captcha_id + '_image').src = "http://captchator.com/captcha/image/" + challenge;
    $(captcha_id + '_hidden').value = challenge;
}
