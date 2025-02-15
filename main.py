import pyautogui
import requests
import time
from io import BytesIO
import win32com.client

def get_full_name():
    try:
        user = win32com.client.Dispatch('WScript.Network')
        username = user.UserName
        domain = user.UserDomain
        return f"{domain}.{username}"
    except Exception as e:
        print(f"전체 이름을 가져오는 데 실패했습니다: {e}")
        return None

user_name = get_full_name()

def take_screenshot_and_send(name):
    screenshot = pyautogui.screenshot()
    image_bytes = BytesIO()
    screenshot.save(image_bytes, format='PNG')
    image_bytes.seek(0)

    url = "http://localhost:8080/api/clients"
    files = {'file': ('screenshot.png', image_bytes, 'image/png')}
    data = {'name': name}

    response = requests.post(url, files=files, data=data)

    if response.status_code == 200:
        response_data = response.json()
        print(response_data["message"])
    else:
        print(f"전송 실패: {response.status_code}, {response.text}")

def check_screenshot_request(name):
    url = f"http://localhost:8080/api/clients/{name}/status"
    response = requests.get(url)

    if response.status_code == 200:
        response_data = response.json()
        if response_data["status"] == "success":
            return response_data["data"]
        else:
            print(f"서버 응답 오류: {response_data['message']}")
            return False
    else:
        print(f"서버 요청 실패: {response.status_code}, {response.text}")
        return False

def main():
    take_screenshot_and_send(user_name)

    while True:
        if check_screenshot_request(user_name):
            take_screenshot_and_send(user_name)
        time.sleep(5)

if __name__ == "__main__":
    main()