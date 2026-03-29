This is the EffinTrak web frontend built with Next.js + TypeScript.

## Local Development

Install dependencies and run the development server:

```bash
npm install
npm run dev
```

Open `http://localhost:3000`.

Set API URL if needed:

```bash
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/api npm run dev
```

## Stack

- Next.js 16 App Router
- TypeScript
- TanStack Query
- Zustand
- React Hook Form + Zod

## Current Implemented Screens

- `/login`
- `/register`
- `/dashboard`
- `/chat`

## Build

```bash
npm run lint
npm run build
```

## Docker

Build frontend image:

```bash
docker build -t effintrak-frontend:latest ./frontend
```

Build with Kubernetes base path (`/app`):

```bash
docker build \
	--build-arg NEXT_PUBLIC_API_BASE_URL=/api \
	--build-arg NEXT_BASE_PATH=/app \
	-t effintrak-frontend:latest ./frontend
```

## Notes

- Backend endpoints are expected under `/api`.
- JWT access/refresh tokens are handled in client storage for now.
- Chat endpoint integration uses non-streaming `/api/chat/prompt`.

## Learn More

To learn more about Next.js, take a look at the following resources:

- [Next.js Documentation](https://nextjs.org/docs) - learn about Next.js features and API.
- [Learn Next.js](https://nextjs.org/learn) - an interactive Next.js tutorial.

You can check out [the Next.js GitHub repository](https://github.com/vercel/next.js) - your feedback and contributions are welcome!

## Deploy on Vercel

The easiest way to deploy your Next.js app is to use the [Vercel Platform](https://vercel.com/new?utm_medium=default-template&filter=next.js&utm_source=create-next-app&utm_campaign=create-next-app-readme) from the creators of Next.js.

Check out our [Next.js deployment documentation](https://nextjs.org/docs/app/building-your-application/deploying) for more details.
