<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Video Database</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 5px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        padding: 10px;
        border-bottom: 1px solid #ddd;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    button {
        display: block;
        margin: 20px auto;
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .video-icon {
        cursor: pointer;
    }

 .button-container {
          display: flex;
          align-items: center;
          justify-content: center;
          flex-wrap: wrap; /* Added to make buttons wrap to the next line if needed */
          margin-top: 80px;
        }

     .enlarge-gif {
                        position: fixed;
                        top: 20px; /* Adjust top position as needed */
                        right: 20px; /* Adjust right position as needed */
                        width: 150px; /* Initial width of the GIF */
                        height: auto; /* Maintains aspect ratio */
                        cursor: pointer;
                        transition: width 0.3s; /* Smooth transition effect */
                    }

                    .enlarge-gif:hover {
                        width: 18%; /* Enlarged width on hover */
                        z-index: 999; /* Ensures the GIF is on top of other elements */
                    }
</style>
</head>
<body>
<div class="container">
    <h1>Video Database</h1>
    <button id="showVideosBtn">Show All Videos in the Database</button>
    <table id="videoTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Content</th>
            </tr>
        </thead>
        <tbody>
            <!-- Data will be populated here -->
        </tbody>
    </table>
</div>
<script>
document.getElementById("showVideosBtn").addEventListener("click", function() {
    // This attaches a click event listener to the element with the ID "showVideosBtn".
    // When this button is clicked, the function inside the event listener will execute.

    fetch("/videopage/ListOfVideos")
    // This initiates a fetch request to the URL "/videopage/ListOfVideos".
    // This URL is assumed to be the endpoint where your backend serves a list of video data.
    // The fetch function returns a Promise that resolves to a Response object.

    .then(response => response.json())
    // Once the response is received from the server, this part of the code converts the response body to JSON format.
    // response.json() returns a Promise that resolves to a JavaScript object representing the JSON data.

    .then(data => {
        // After the JSON data is successfully parsed, this function is called with the parsed data as its argument.
        // The parsed JSON data is stored in the variable "data".

        const tableBody = document.querySelector("#videoTable tbody");
        // This line selects the <tbody> element inside the table with the ID "videoTable".
        // It will be used to populate the table with video data.

        tableBody.innerHTML = "";
        // This clears any existing content inside the <tbody> element.
        // This ensures that we start with an empty table before populating it with new video data.

        data.forEach(video => {
            // This loops over each item in the "data" array.
            // Assuming "data" is an array of objects representing videos.

            const row = document.createElement("tr");
            // For each video, it creates a new table row element (<tr>).

            let aid = video.id;
            let aname = video.name;
            let adescription = video.descrip;
           // let acontentlink = video.contentLink;
             let acontentlink = "/videopage/video/"+ aname;
             //alert(acontentlink);
            // Extracting information about each video from the data object.

            row.innerHTML = '<td>' + aid + '</td>' +
                            '<td>' + aname + '</td>' +
                            '<td>' + adescription + '</td>' +
                            '<td><span class="video-icon" data-url="' + acontentlink + '"> &#128250 </span> <span class="downloadButton" style="cursor: pointer;">  &#x1F4E5;</span></td>';
            // It sets the inner HTML of the table row with the video information.
            // It dynamically inserts the ID, name, description, and a video icon (<span>) with the URL to the video content.

            tableBody.appendChild(row);
            // Finally, it appends the newly created table row to the <tbody> element.
            // This adds the video information to the table.
        });

        // Add event listener to video icons
        document.querySelectorAll('.video-icon').forEach(icon => {
            icon.addEventListener('click', function() {


                const videoUrl = this.getAttribute('data-url');

                // When a video icon is clicked, this function is executed.
                // It retrieves the data-url attribute of the clicked icon, which contains the URL to the video content.

                fetch(videoUrl, {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/octet-stream'
                    }
                })
                // This fetches the video content as a blob.
                // The 'Accept' header specifies that the client expects the response to be in the form of an octet stream (binary data).

                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.blob();
                })
                // Once the response is received, it checks if the response is successful.
                // If successful, it returns the response body as a blob.

                .then(blob => {
                    // This function is executed after the blob data is received.

                    const blobUrl = URL.createObjectURL(blob);
                    // It creates a blob URL for the video content.
                    // This URL can be used to stream the video in the browser.

                    const newTab = window.open();
                    // It opens a new tab.

                    newTab.document.write(
                        '<!DOCTYPE html>' +
                        '<html>' +
                        '<head><title>Video Player</title></head>' +
                        '<body style="margin: 0;">' +
                        '<video controls style="width: 100%; height: 100vh;">' +
                        '<source src="' + blobUrl + '" type="video/mp4">' +
                        'Your browser does not support the video tag.' +
                        '</video>' +
                        '</body>' +
                        '</html>'
                    );
                    // It writes HTML content to the new tab.
                    // The content includes a video element with controls and the source set to the blob URL.

                    newTab.document.close();
                    // It closes the document opened with the write method.
                })
                .catch(error => console.error("Error fetching video:", error));
                // If there's an error during the fetch request or processing of the blob data, this part catches the error and logs it to the console.
            });
        });

        // Add event listener to download buttons
        document.querySelectorAll('.downloadButton').forEach(downloadButton => {
            downloadButton.addEventListener('click', function(event) {
                event.stopPropagation(); // Prevent the click event from bubbling to the video icon

                const videoUrl = this.parentNode.querySelector('.video-icon').getAttribute('data-url');
                window.location.href = videoUrl;
            });
        });

    })
    .catch(error => console.error("Error fetching videos:", error));
    // If there's an error during the fetch request or parsing of the JSON response, this part catches the error and logs it to the console.
});
</script>




<script>
    function myFunction() {
      window.location.href = "/streammusic";
    }

    function myFunction1() {
      window.location.href = "/uploadmusic";
    }

    function myFunction2() {
      window.location.href = "/streamvideo";
    }

    function myFunction3() {
      window.location.href = "/uploadvideo";
    }
  </script>

<div class="button-container">
    <button class="button button1" onclick="myFunction()"> &#128251 Go to Music Streaming Page</button>
    <button class="button button2" onclick="myFunction1()"> &#127927 Go to Music Upload Page</button>
    <button class="button button3" onclick="myFunction2()"> &#128250 Go to Video Streaming Page</button>
    <button class="button button4" onclick="myFunction3()"> &#127909 Go to Video Upload Page</button>
  </div>

<!-- Add the GIF image -->
    <a href="/ListMusics">
        <img src="GIFS_Webpage/MusicRelated/JerryListening Music.gif" class="enlarge-gif" alt="Enlarge" title="Click to redirect to Music Database">
    </a>


</body>
</html>
