from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/process_payment', methods=['POST'])
def process_payment():
    data = request.json
    if not data or 'amount' not in data or data['amount'] <= 0:
        return jsonify({'status': 'failure', 'message': 'Invalid payment data'}), 400

    return jsonify({'status': 'success', 'message': 'Payment processed successfully'})

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)
