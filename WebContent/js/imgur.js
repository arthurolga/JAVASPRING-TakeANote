function getBase64(file, onLoadCallback) {
    return new Promise(function(resolve, reject) {
        var reader = new FileReader();
        reader.onload = function() { resolve(reader.result); };
        reader.onerror = reject;
        reader.readAsDataURL(file);
    });
}

inputElement = document.querySelectorAll('input[type=file]')
inputElements = [...inputElement]
inputElements.forEach((e) => {
	e.addEventListener('change', async (event) => {
   		let file = event.target.files[0]
        let header = new Headers()
        let fd = new FormData()
        fd.append("image", file);
        header.append("Authorization","Client-ID c617d0edea36c6f");
        let res = await fetch('https://api.imgur.com/3/image.json',{
            method : 'POST',
            headers: header,
            body: fd
        })
		res = await res.json()
        console.log(res)
        console.log(e.id)
        let link = res.data.link
        document.getElementById("link"+e.id).value = link 
	})
})