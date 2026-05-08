import { type NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_URL || "http://app:8080";

async function handler(
	req: NextRequest,
	{ params }: { params: Promise<{ path: string[] }> },
) {
	const { path } = await params;
	const target = `${BACKEND_URL}/api/${path.join("/")}${req.nextUrl.search}`;

	const headers = new Headers(req.headers);
	// Remove browser headers that cause CORS issues on the backend
	headers.delete("host");
	headers.delete("origin");
	headers.delete("referer");

	try {
		const res = await fetch(target, {
			method: req.method,
			headers,
			body: req.body,
			// @ts-expect-error -- duplex is required for streaming body
			duplex: "half",
		});

		const responseHeaders = new Headers(res.headers);
		// Remove transfer-encoding as Next.js handles it
		responseHeaders.delete("transfer-encoding");

		return new NextResponse(res.body, {
			status: res.status,
			statusText: res.statusText,
			headers: responseHeaders,
		});
	} catch (err) {
		console.error("[api-proxy] Error proxying to backend:", target, err);
		return NextResponse.json(
			{ message: "Backend unavailable" },
			{ status: 502 },
		);
	}
}

export const GET = handler;
export const POST = handler;
export const PUT = handler;
export const PATCH = handler;
export const DELETE = handler;
