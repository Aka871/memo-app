// DOMが読み込まれた後に実行
document.addEventListener('DOMContentLoaded', () => {
    // メモ一覧を取得して表示
    fetchMemos();
});

// メモ一覧を取得する関数
async function fetchMemos() {
    try {
        const response = await fetch('/memos');
        const memos = await response.json();
        displayMemos(memos);
    } catch (error) {
        console.error('Error:', error);
    }
}

// メモ一覧を表示する関数
function displayMemos(memos) {
    const memosDiv = document.getElementById('memos');
    memosDiv.innerHTML = '';
    memos.forEach(memo => {
        const memoElement = document.createElement('div');
        memoElement.className = 'memo';
        memoElement.innerHTML = `
            <h3>${memo.title}</h3>
            <p>${memo.content}</p>
            <small>作成日時: ${new Date(memo.createdAt).toLocaleString()}</small>
        `;
        memosDiv.appendChild(memoElement);
    });
}
