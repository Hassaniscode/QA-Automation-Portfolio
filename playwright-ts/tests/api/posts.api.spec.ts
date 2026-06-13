import { test, expect } from '@playwright/test';

const BASE_URL = 'https://jsonplaceholder.typicode.com';

test.describe('Posts API', () => {
  test('GET /posts - should return all posts', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBe(100);
    expect(body[0]).toHaveProperty('userId');
    expect(body[0]).toHaveProperty('id');
    expect(body[0]).toHaveProperty('title');
    expect(body[0]).toHaveProperty('body');
  });

  test('GET /posts?userId=1 - should filter posts by user', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts`, {
      params: { userId: 1 },
    });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBeGreaterThan(0);
    body.forEach((post: { userId: number }) => {
      expect(post.userId).toBe(1);
    });
  });

  test('GET /posts/:id/comments - should return comments for a post', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts/1/comments`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBeGreaterThan(0);
    expect(body[0]).toHaveProperty('postId');
    expect(body[0]).toHaveProperty('email');
    expect(body[0]).toHaveProperty('body');
    expect(body[0].postId).toBe(1);
  });

  test('POST /posts - should create a new post', async ({ request }) => {
    const response = await request.post(`${BASE_URL}/posts`, {
      data: {
        title: 'API Testing with Playwright',
        body: 'Demonstrating API test automation',
        userId: 1,
      },
    });

    expect(response.status()).toBe(201);
    const body = await response.json();
    expect(body.title).toBe('API Testing with Playwright');
    expect(body.userId).toBe(1);
    expect(body.id).toBeTruthy();
  });

  test('PUT /posts/:id - should replace a post', async ({ request }) => {
    const response = await request.put(`${BASE_URL}/posts/1`, {
      data: {
        id: 1,
        title: 'Updated Title',
        body: 'Updated body content',
        userId: 1,
      },
    });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.title).toBe('Updated Title');
    expect(body.body).toBe('Updated body content');
  });

  test('DELETE /posts/:id - should delete a post', async ({ request }) => {
    const response = await request.delete(`${BASE_URL}/posts/1`);

    expect(response.status()).toBe(200);
  });
});
