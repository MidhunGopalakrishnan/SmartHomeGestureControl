from flask import Flask, request, redirect
import os

app = Flask(__name__)
app.config["VIDEO_UPLOADS"] = "/Users/midhungopalakrishnan/IdeaProjects/FlaskTestServer/videos/uploads"


@app.route("/upload-videos", methods=["GET", "POST"])
def upload_videos():
    if request.method == "POST":
        if request.files:
            for file in request.files:
                print(file)
                video = request.files[file]
                video.save(os.path.join(app.config["VIDEO_UPLOADS"], video.filename))
                print("Video Saved")
            return redirect(request.url)

    else:
        return "Upload Video using POST method"


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
